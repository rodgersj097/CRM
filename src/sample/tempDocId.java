package sample;

public class tempDocId {
    Integer tempdocId, existingDocId;
    String existingdocName, tempDocName;

    public tempDocId(Integer tempdocId, Integer existingDocId, String existingdocName, String tempDocName) {
        this.tempdocId = tempdocId;
        this.existingDocId = existingDocId;
        this.existingdocName = existingdocName;
        this.tempDocName = tempDocName;
    }

    public Integer getExistingDocId() {
        return existingDocId;
    }

    public void setExistingDocId(Integer existingDocId) {
        this.existingDocId = existingDocId;
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
