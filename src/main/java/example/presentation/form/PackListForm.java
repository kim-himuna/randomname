package example.presentation.form;

import java.util.List;

public class PackListForm {
    private long id;
    private String title;
    private List<String> words;
    private boolean using;

    public PackListForm(long id,String title,List<String> words,boolean using){
        this.id = id;
        this.title = title;
        this.words = words;
        this.using = using;
    }

    public long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public List<String> getWords(){
        return words;
    }

    public boolean getUsing(){
        return using;
    }
    public void setUsing(boolean using){
        using = this.using;
    }
}
