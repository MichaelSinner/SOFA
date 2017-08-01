/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uan.OS;

/**
 *
 * @author DCA_3
 */
public class FileInformation {
    
    private String FileName, Type, Path, CreateDate, ModifyDate, Size;

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getModifyDate() {
        return ModifyDate;
    }

    public void setModifyDate(String ModifyDate) {
        this.ModifyDate = ModifyDate;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }
    
}
