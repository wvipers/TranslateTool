import com.google.gson.internal.LinkedHashTreeMap;

/**
 * Created by Patryk on 21.05.2017.
 */
public class UneditableTranslationMap extends TranslationMap {

    UneditableTranslationMap(LinkedHashTreeMap<String,String> map){
        super(map);
    }

    UneditableTranslationMap(){
        super();
    }

}
