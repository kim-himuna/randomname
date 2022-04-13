package example.domain.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Session {
    private ShuffleList shuffleList;
    private String searchWord;
    
    public ShuffleList getShuffleList(){

        if(shuffleList == null){
            shuffleList = new ShuffleList();
        }
        return shuffleList;
    }

    public String getSearchWord(){

        if(searchWord == null){
            searchWord = "";
        }
        return searchWord;
    }


    public void setShuffleList(ShuffleList shuffleList){
        this.shuffleList = shuffleList;
    }

    public void setSearchWord(String searchWord){
        this.searchWord = searchWord;
    }



    public void clearShuffleList(){
    }




}
