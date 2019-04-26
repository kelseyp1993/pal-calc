package io.pivotal.pal.calc;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCalcEntryRepository implements CalcEntryRepository{

    public List<CalcEntry> calcHistory = new ArrayList<>();

    @Override
    public CalcEntry create(CalcEntry calcEntryToCreate) {
        double calcResult = 0;
        switch(calcEntryToCreate.getOperator()){
            case 0:
                calcResult =
                        calcEntryToCreate.getOperandOne() +
                                calcEntryToCreate.getOperandTwo();
                break;
            case 1:
                calcResult =
                        calcEntryToCreate.getOperandOne() -
                                calcEntryToCreate.getOperandTwo();
                break;
            case 2:
                calcResult =
                        calcEntryToCreate.getOperandOne() *
                                calcEntryToCreate.getOperandTwo();
                break;
            case 3:
                calcResult =
                        calcEntryToCreate.getOperandOne() /
                                calcEntryToCreate.getOperandTwo();
                break;
        }

        CalcEntry newCalcEntry = new CalcEntry(calcEntryToCreate.getOperandOne(),
                            calcEntryToCreate.getOperandTwo(),
                            calcEntryToCreate.getOperator(),
                            calcResult);

        calcHistory.add(0,newCalcEntry);
        return newCalcEntry;
    }

    @Override
    public List<CalcEntry> list() {
        return calcHistory;
    }
}