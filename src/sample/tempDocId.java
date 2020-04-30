package sample;

public class tempDocId {
    Integer tempdocId;
    String existingdocName, tempDocName;

    public tempDocId(Integer tempdocId, String existingdocName, String tempDocName) {
        this.tempdocId = tempdocId;
        this.existingdocName = existingdocName;
        this.tempDocName = tempDocName;
    }

    public Integer getTempdocId() {
        return tempdocId;
    }

    public void setTempdocId(Integer tempdocId) {
        this.tempdocId = tempdocId;
    }

    public String getExistingdocName() {
        return existingdocName;
    }

    public void setExistingdocName(String existingdocName) {
        this.existingdocName = existingdocName;
    }

    public String getTempDocName() {
        return tempDocName;
    }

    public void setTempDocName(String tempDocName) {
        this.tempDocName = tempDocName;
    }
}
