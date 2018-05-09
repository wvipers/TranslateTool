import java.util.ArrayList;

/**
 * Created by Patryk on 27.05.2017.
 */
public class VisibleTranslationList {

    public interface UpdateVisibleView{
        void update();
    }

    private UneditableTranslationMap sourceTranslationsMap;
    private EditableTranslationMap improvedTranslationsMap;
    private ArrayList<String> visibleKeys;
    private boolean allVisible;


    VisibleTranslationList(UneditableTranslationMap srcMap,EditableTranslationMap improvedMap){
        sourceTranslationsMap=srcMap;
        improvedTranslationsMap=improvedMap;
        visibleKeys=new ArrayList<String>();
        seeWrongTranslations();
    }

    private UpdateVisibleView updateVisibleView;

    private ArrayList<String> getKeysMissingTranslations(){
        ArrayList<String> missingKeys= new ArrayList<String>();
        for(String key : sourceTranslationsMap.keySet()){
            if(!improvedTranslationsMap.getMap().containsKey(key))missingKeys.add(key);
    }
        return missingKeys;
    }

    private ArrayList<String> getKeysRepeatedTranslations(){
        ArrayList<String> repeatedKeys= new ArrayList<String>();
        for(String key : sourceTranslationsMap.keySet()){
            if(improvedTranslationsMap.get(key)==sourceTranslationsMap.get(key))repeatedKeys.add(key);
        }
        return repeatedKeys;
    }



    public void seeAllTranlations(){
        visibleKeys.clear();
        visibleKeys.addAll(sourceTranslationsMap.keySet());
        allVisible=true;
        if(updateVisibleView!=null)updateVisibleView.update();
    }

    public void seeWrongTranslations(){
        visibleKeys.clear();
        visibleKeys=getKeysMissingTranslations();
        visibleKeys.addAll(getKeysRepeatedTranslations());
        allVisible=false;
        if(updateVisibleView!=null)updateVisibleView.update();
    }

    public String get(int i){
        return visibleKeys.get(i);
    }

    public int size(){
        return visibleKeys.size();
    }

    public void setOnUpdateView(UpdateVisibleView u){
        updateVisibleView=u;
    }

    public void update(){
        if(allVisible)seeAllTranlations();
        else seeWrongTranslations();
    }

    public boolean isAllVisible(){
        return allVisible;
    }

}
