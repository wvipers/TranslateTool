
public class Saver {
    public static void save(EditableTranslationMap improvedTranslationMap,EditableTranslationMap editedTranslationMap,String improvedFileURI){
        improvedTranslationMap.upgradeFrom(editedTranslationMap);
        TranslationReader.saveTranslationMap(improvedTranslationMap,improvedFileURI);
        TranslateTool.isSaved=true;
    }


}
