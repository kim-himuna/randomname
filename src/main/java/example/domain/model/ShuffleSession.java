package example.domain.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


@Component
@Scope(value="session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShuffleSession {
    private ShuffleList shuffleList;
    
    public ShuffleList getShuffleList(){

        if(shuffleList == null){
            shuffleList = new ShuffleList();
        }
        return shuffleList;
    }

    public void setShuffleList(ShuffleList shuffleList){
        this.shuffleList = shuffleList;

    }

    public void clearShuffleList(){
    }


}
