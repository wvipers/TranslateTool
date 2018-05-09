import com.google.gson.internal.LinkedHashTreeMap;

import javax.swing.*;
import java.awt.*;


public class TranslateTool {

    public static boolean isSaved=true;

    public static void main(String [] args) {



        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TranslationToolFrame();
            }
        });


    }
}
