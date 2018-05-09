import javax.swing.table.AbstractTableModel;

public class EditorTableModel extends AbstractTableModel {

    String[] columnNames={"Key","Source","Current","New"};

    UneditableTranslationMap srcTranslationMap;
    EditableTranslationMap editedTranslationMap, improvedTranslationMap;
    VisibleTranslationList visibleTranslationsKeys;




    EditorTableModel(UneditableTranslationMap srcMap,EditableTranslationMap improvedMap, EditableTranslationMap editedMap,VisibleTranslationList visible){
        super();
        this.srcTranslationMap=srcMap;
        this.improvedTranslationMap=improvedMap;
        this.editedTranslationMap=editedMap;


        visibleTranslationsKeys=visible;
        srcTranslationMap.setOnUpdate(()->this.fireTableDataChanged());
        improvedTranslationMap.setOnUpdate(()->this.fireTableDataChanged());
        visibleTranslationsKeys.setOnUpdateView(()->this.fireTableDataChanged());
    }

    public int getColumnCount(){
        return columnNames.length;
    }
    public int getRowCount()     {
        return visibleTranslationsKeys.size();
    }
    public String getColumnName(int col){
        return columnNames[col];
    }
    public Class getColumnClass(int col){
        return getValueAt(col,0).getClass();
    }
    public boolean isCellEditable(int row, int col){
        if(col==3)return true;
        else return false;
    }

    public Object getValueAt(int row, int col) {
        switch(col){
            case 0:
                //return srcTranslationMap.convertToArrayList().get(row).get(0);
                return visibleTranslationsKeys.get(row);
            case 1:
                //return srcTranslationMap.convertToArrayList().get(row).get(1);
                return srcTranslationMap.get(visibleTranslationsKeys.get(row));
            case 2:
               // if(editedTranslationMap.convertToArrayList().size()>row)
                    return improvedTranslationMap.get(visibleTranslationsKeys.get(row));
               // else return "";
            case 3:
                return editedTranslationMap.get(visibleTranslationsKeys.get(row));
        }
        return null;
    }
    public void setValueAt(Object value, int i, int j){
        editedTranslationMap.putTranslation(visibleTranslationsKeys.get(i),(String)value);
        TranslateTool.isSaved=false;
    }




}
