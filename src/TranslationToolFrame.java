import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class TranslationToolFrame extends JFrame {

    String improvedFileURI=new String();
    EditableTranslationMap improvedTranslationMap= new EditableTranslationMap();
    EditableTranslationMap editedTranslationMap = new EditableTranslationMap();
    UneditableTranslationMap srcTranslationMap= new UneditableTranslationMap();
    VisibleTranslationList visibleTranslationsKeys= new VisibleTranslationList(srcTranslationMap,improvedTranslationMap);
    JPanel buttonPanel;
    EditorTableModel tableModel;
    JTable editorTable;

    TranslationToolFrame(){
        super("Translation Tool");
        tableModel=new EditorTableModel(srcTranslationMap,improvedTranslationMap, editedTranslationMap,visibleTranslationsKeys);
        editorTable=new JTable(tableModel);
        buttonPanel=new ButtonPanel(srcTranslationMap,improvedTranslationMap,editedTranslationMap,visibleTranslationsKeys,improvedFileURI);

        setMinimumSize(new Dimension(1200,550));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setWindowClosingOperation();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx=900;
        c.gridx=0;
        c.gridy=0;
        add(new JScrollPane(editorTable),c);
        c.fill=GridBagConstraints.VERTICAL;
        c.gridx=1;
        c.weightx=100;
        add(buttonPanel,c);

        pack();
        setVisible(true);

    }



    private void setWindowClosingOperation(){
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int i=1;
                if(!TranslateTool.isSaved){
                    i=JOptionPane.showConfirmDialog(null, "Your work is not saved. Do you want save it?");
                }
                if(i==0) {
                    Saver.save(improvedTranslationMap,editedTranslationMap,improvedFileURI);
                    System.exit(0);
                }
                else if(i==1){
                    System.exit(0);
                }
                else return;
            }
        });
    }
}
