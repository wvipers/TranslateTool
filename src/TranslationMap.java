import com.google.gson.internal.LinkedHashTreeMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;


public abstract class TranslationMap {


    public interface UpdateTranslationMap {
        void update();
    }

    protected UpdateTranslationMap updateModule;

    protected LinkedHashTreeMap<String,String> map;

    TranslationMap(){
        this.map=new LinkedHashTreeMap<String,String>();
    }

    TranslationMap(LinkedHashTreeMap<String,String> map){
        this.map=map;
    }

    public LinkedHashTreeMap<String, String> getMap(){
        return map;
    }

    public Set<String> keySet(){
        return map.keySet();
    }

    public Collection<String> values(){
        return map.values();
    }

    public String get(String key){
        return map.get(key);
    }

    public void setMap(LinkedHashTreeMap<String, String> map) {
        this.map = map;
        if(updateModule!=null)updateModule.update();

    }

    public boolean containsKey(String key){
        return map.containsKey(key);
    }

    public int size(){
        return map.size();
    }

    public ArrayList<ArrayList<String>> convertToArrayList(){
        ArrayList<ArrayList<String>> arrayList=new ArrayList<ArrayList<String>>();

        int i=0;

        for(String key : this.map.keySet()){
            arrayList.add(new ArrayList<String>());
            arrayList.get(i).add(key);
            arrayList.get(i).add(this.map.get(key));
            i++;
        }
        return arrayList;

    }


    public LinkedHashMap<String,String> convertToLinkedHashMap(){
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<String,String>();
        int i=0;

        for(String key : this.map.keySet()){
            linkedHashMap.put(key,map.get(key));
            i++;
        }
        return linkedHashMap;
    }

    public void setOnUpdate(UpdateTranslationMap u){
        updateModule=u;
    }
}
