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

    public PackListForm(long id,String title,List<String> words,boolean isUsed){
        this.id = id;
        this.title = title;
        this.words = words;
        this.isUsed = isUsed;
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

    public boolean getIsUsed(){
        return isUsed;
    }
    public void setIsUsed(boolean isUsed){
        isUsed = this.isUsed;
    }
}
