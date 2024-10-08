package model.mnm.api;

import java.util.ArrayList;

public class ComponentBody {
    private ArrayList<Parameter> parameters;

    public ComponentBody(ArrayList<Parameter> parameters) {
        this.parameters = parameters;
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameters = parameters;
    }
}
