package spark;

import java.io.Serializable;

/**
 * Created by Naks on 30-Apr-16.
 * POJO for cassandra
 */
public class WordCount implements Serializable {

    private String word;
    private Integer count;

    public WordCount(String word, Integer count) {
        this.word = word;
        this.count = count;
    }

    public WordCount() {

    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
