/*packを同時に複数使用できないように，使用中かどうか判定するisUsedを追加したpackmodel
    formに入れるのも微妙だが，domainのpackにいれていいものか？
*/

package example.presentation.form;

import java.util.List;

public class PackListForm {
    private long id;
    private String title;
    private List<String> words;
    private boolean isUsed;
    private boolean isLiked;
    private String creator;

    public PackListForm(long id,String title,List<String> words,String creator,boolean isUsed,boolean isLiked){
        this.id = id;
        this.title = title;
        this.words = words;
        this.isUsed = isUsed;
        this.isLiked = isLiked;
        this.creator = creator;
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

    public String getCreator(){
        return creator;
    }

    public boolean getIsUsed(){
        return isUsed;
    }
    public void setIsUsed(boolean isUsed){
        this.isUsed = isUsed;
    }

    public boolean getIsLiked(){
        return isLiked;
    }
    public void setIsLiked(boolean isLiked){
        this.isLiked = isLiked;
    }
}
