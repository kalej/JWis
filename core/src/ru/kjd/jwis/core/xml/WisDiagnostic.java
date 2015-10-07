package ru.kjd.jwis.core.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="diagnostic")
public class WisDiagnostic {
    private String step;
    private String entry;
    private String sympDesc;
    private List<String> fcodes;

    public String getStep() {
        return step;
    }

    @XmlAttribute(name = "step")
    public void setStep(String step) {
        this.step = step;
    }

    public String getEntry() {
        return entry;
    }

    @XmlAttribute(name = "entry")
    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getSympDesc() {
        return sympDesc;
    }

    @XmlElement(name = "sympdesc")
    public void setSympDesc(String sympDesc) {
        this.sympDesc = sympDesc;
    }

    public List<String> getFcodes() {
        return fcodes;
    }

    @XmlElement(name = "fcode")
    public void setFcodes(List<String> fcodes) {
        this.fcodes = fcodes;
    }
}
