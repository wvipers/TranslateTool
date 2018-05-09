
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;


public class ButtonPanel extends JPanel implements ActionListener{
    private final int openSrcFileButton=0, openEditedFileButton=1, seeAllButton=2, saveButton=3, saveAsButton=4;
    private ArrayList<JButton> buttons;
    public static final int HEIGHT = 400;
    public static final int WIDTH = 160;
    private UneditableTranslationMap srcTranslationMap;
    private EditableTranslationMap improvedTranslationMap,editedTranslationMap;
    private VisibleTranslationList visibleTranslationsKeys;
    private String improvedFileURI;

    ButtonPanel(UneditableTranslationMap srcMap,EditableTranslationMap improvedMap,EditableTranslationMap editedMap, VisibleTranslationList visible,String improvedFileURI){
        srcTranslationMap=srcMap;
        improvedTranslationMap=improvedMap;
        editedTranslationMap=editedMap;
        visibleTranslationsKeys=visible;
        this.improvedFileURI=improvedFileURI;


        buttons=new ArrayList<JButton>();
        buttons.add(new JButton("Open source file"));
        buttons.add(new JButton("Open file to edit"));
        buttons.add(new JButton("See all / see wrong"));
        buttons.add(new JButton("Save"));
        buttons.add(new JButton("Save as"));

        for(int i=0;i<buttons.size();i++){
            buttons.get(i).setPreferredSize(new Dimension(145,40));
            buttons.get(i).addActionListener(this);
            add(buttons.get(i));
        }

        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        JFileChooser fileChooser=new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON File","json");
        fileChooser.setFileFilter(filter);

        if(source == buttons.get(openSrcFileButton)) {
            int returnVal = fileChooser.showOpenDialog(ButtonPanel.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                srcTranslationMap.setMap(TranslationReader.readTranslationMap(file.getAbsolutePath()));
                visibleTranslationsKeys.update();
                System.out.println("Opening: " + file.getName() );
            } else {
                System.out.println("Open command cancelled by user." );
            }
        }
        else if(source == buttons.get(openEditedFileButton)){
            int returnVal = fileChooser.showOpenDialog(ButtonPanel.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                improvedFileURI=file.getAbsolutePath();
                improvedTranslationMap.setMap(TranslationReader.readTranslationMap(improvedFileURI));
                visibleTranslationsKeys.update();
                System.out.println("Opening: " + file.getName() );
            } else {
                System.out.println("Open command cancelled by user." );
            }
        }

        else if(source == buttons.get(seeAllButton)){
            if(visibleTranslationsKeys.isAllVisible())visibleTranslationsKeys.seeWrongTranslations();
            else visibleTranslationsKeys.seeAllTranlations();

        }



        else if(source == buttons.get(saveButton)){
            if(srcTranslationMap.size()!=0&&editedTranslationMap.size()!=0) {
                Saver.save(improvedTranslationMap, editedTranslationMap, improvedFileURI);
            }
        }

        else if (source==buttons.get(saveAsButton)){
            if(srcTranslationMap.size()!=0&&editedTranslationMap.size()!=0){
                int returnVal = fileChooser.showOpenDialog(ButtonPanel.this);

                if (returnVal == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    improvedFileURI=file.getAbsolutePath();
                    Saver.save(improvedTranslationMap,editedTranslationMap,improvedFileURI);
                }
            }
        }



    }
}
