import com.google.gson.internal.LinkedHashTreeMap;


public class EditableTranslationMap extends TranslationMap{

    EditableTranslationMap(LinkedHashTreeMap<String,String> map){
        super(map);
    }

    EditableTranslationMap(){
        super();
    }


    public void putTranslation(String key,String value){
        map.put(key,value);
    }

    public void upgradeFrom(EditableTranslationMap editedMap){
        for(String key:editedMap.keySet()){
            if(!this.map.containsKey(key))this.map.put(key,editedMap.get(key));
            if (this.map.get(key)!=editedMap.get(key)&&editedMap.get(key)!=""&&editedMap.get(key)!=null)this.map.put(key,editedMap.get(key));
        }
        editedMap.clear();
        updateModule.update();
    }

    public void clear(){
        map.clear();
    }



}
