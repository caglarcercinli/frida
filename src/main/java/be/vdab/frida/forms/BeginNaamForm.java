package be.vdab.frida.forms;

import javax.validation.constraints.NotBlank;

public class BeginNaamForm {
    @NotBlank
    private final String beginNaam;

    public BeginNaamForm(String beginNaam) {
        this.beginNaam = beginNaam;
    }

    public String getBeginNaam() {
        return beginNaam;
    }
}
