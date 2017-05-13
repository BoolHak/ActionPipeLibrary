package com.youmait.demoactionpipe.domain;

import com.youmait.demoactionpipe.domain.Actions.CheckInternetAction;
import com.youmait.demoactionpipe.domain.Actions.MathOperationAction;
import com.youmait.demoactionpipe.domain.Actions.SaveDataAction;
import com.youmait.demoactionpipe.domain.Actions.WebServiceAction;
import com.youmait.pipe.actions.AbstractAction;
import com.youmait.pipe.pipe.AbstractPipe;

public class MainPipe extends AbstractPipe{

    AbstractAction checkInternet;
    AbstractAction mathOperation;
    AbstractAction saveData;
    AbstractAction webService;

    public MainPipe(){
        super();
    }

    @Override
    protected void initActions() {
        checkInternet = new CheckInternetAction(this).retry(2).progress(10);
        mathOperation = new MathOperationAction(this).progress(40);
        saveData = new SaveDataAction(this).progress(60);
        webService = new WebServiceAction(this).retry(3).progress(100);
    }

    @Override
    protected void linkActions() {
        checkInternet.next(mathOperation).failed("Check your internet connection!");
        mathOperation.next(saveData).failed("We can not make this calculation");
        saveData.next(webService).failed("We can not save data to disk");
        webService.failed(checkInternet).isFinal();
    }

    @Override
    public void startProcess() {
        startWithAction(checkInternet);
    }

}
