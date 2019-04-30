package io.pivotal.pal.calc;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCalcEntryRepository implements CalcEntryRepository{


    public List<CalcEntry> calcHistory = new ArrayList<>();

    public InMemoryCalcEntryRepository(){

    }

    @Override
    public CalcEntry create(CalcEntry calcEntryToCreate) {
        double calcResult = 0;
        switch(calcEntryToCreate.getOperator()){
            case 0: //Add
                calcResult =
                        calcEntryToCreate.getOperandOne() +
                                calcEntryToCreate.getOperandTwo();
                break;
            case 1: //Sub
                calcResult =
                        calcEntryToCreate.getOperandOne() -
                                calcEntryToCreate.getOperandTwo();
                break;
            case 2: //Mult
                calcResult =
                        calcEntryToCreate.getOperandOne() *
                                calcEntryToCreate.getOperandTwo();
                break;
            case 3: //Div
                calcResult =
                        calcEntryToCreate.getOperandOne() /
                                calcEntryToCreate.getOperandTwo();
                break;
        }

//        CalcEntry newCalcEntry = new CalcEntry(calcEntryToCreate.getOperandOne(),
//                            calcEntryToCreate.getOperandTwo(),
//                            calcEntryToCreate.getOperator(),
//                            calcResult);

        calcEntryToCreate.setResult(calcResult);
        calcHistory.add(0,calcEntryToCreate);
        return calcEntryToCreate;
    }

    @Override
    public List<CalcEntry> list() {
        return calcHistory;
    }
}
