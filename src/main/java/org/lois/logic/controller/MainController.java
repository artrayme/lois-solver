package org.lois.logic.controller;

import org.lois.logic.logics.FourDigitLogic;
import org.lois.logic.logics.ThreeDigitLogic;
import org.lois.logic.parser.LEParser;
import org.lois.logic.parser.exceptions.InvalidAtomicExpressionSyntaxException;
import org.lois.logic.parser.exceptions.InvalidBracketsException;
import org.lois.logic.parser.exceptions.InvalidOperatorException;
import org.lois.logic.parser.exceptions.InvalidSyntaxCharacterException;
import org.lois.logic.parser.tree.LETree;
import org.lois.logic.table.LogicTableGenerator;
import org.lois.logic.table.TableEntry;
import org.lois.logic.table.utils.FourDigitLogicStateGenerator;
import org.lois.logic.table.utils.TripletLogicStateGenerator;
import org.lois.logic.utils.BitConverterUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Controller
@RequestMapping(value = "")
public class MainController {

    private List<List<String>> logic3Result = new ArrayList<>();
    private List<List<String>> logic4Result = new ArrayList<>();
    private String message = "";


    @GetMapping()
    public String creator(Model model) {


        model.addAttribute("logic3", logic3Result);
        model.addAttribute("logic4", logic4Result);
        model.addAttribute("message", this.message);
        model.addAttribute("formula", new FormulaDTO(""));

        return "index";
    }

    @PostMapping()
    public String create(Model model, @ModelAttribute("formula") FormulaDTO formula) {
//        var value = "((A∨(!A))∨(B∧(!B)))";
        try {
            LETree tree = LEParser.valueOf(formula.getFormula());
            tree.setLogicProxy(new ThreeDigitLogic());
            var tableGenerator = new LogicTableGenerator();
            var table3 = tableGenerator.generateTruthTable(
                    tree,
                    new TripletLogicStateGenerator(tree.getValues().keySet().size())
            );

            tree.setLogicProxy(new FourDigitLogic());
            var table4 = tableGenerator.generateTruthTable(
                    tree,
                    new FourDigitLogicStateGenerator(tree.getValues().keySet().size())
            );

            logic3Result = new ArrayList<>();
            logic3Result.add(Stream.concat(tree.getValues().keySet().stream(), Stream.of("Result3")).toList());
            for (TableEntry tableEntry : table3) {
                logic3Result.add(
                        Stream.concat(
                                tableEntry.input().values().stream().map(e-> BitConverterUtils.toTripletLogicNumber(e).toString()),
                                Stream.of(BitConverterUtils.toTripletLogicNumber(tableEntry.result()).toString())).toList());
            }

            logic4Result = new ArrayList<>();
            logic4Result.add(Stream.concat(tree.getValues().keySet().stream(), Stream.of("Result4")).toList());
            for (TableEntry tableEntry : table4) {
                logic4Result.add(
                        Stream.concat(
                                tableEntry.input().values().stream().map(e-> BitConverterUtils.toFourDigitLogicNumber(e).toString()),
                                Stream.of(BitConverterUtils.toFourDigitLogicNumber(tableEntry.result()).toString())).toList());
            }
            message = "";
        } catch (InvalidBracketsException | InvalidSyntaxCharacterException | InvalidAtomicExpressionSyntaxException |
                 InvalidOperatorException e) {
            this.message = e.getMessage();
            logic3Result = List.of();
            logic4Result = List.of();
        }
        model.addAttribute("logic3", logic3Result);
        model.addAttribute("logic4", logic4Result);
        model.addAttribute("message", this.message);
        model.addAttribute("formula", formula);
        return "index";
    }
}
