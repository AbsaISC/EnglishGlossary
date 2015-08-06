package com.isc.absa.englishglossary.dto;

/**
 * Created by Absalom on 06/08/2015.
 */
public class GlossaryDTO {
    private long _ID;
    private Integer id;
    private String word;
    private String pos;
    private String meaning;
    private String example;
    private String es_word;
    private String date;
    private String title;
    private String subtitle;

    public GlossaryDTO(long _ID, Integer id, String word, String pos, String meaning, String example, String es_word, String date, String title, String subtitle) {
        this._ID = _ID;
        this.id = id;
        this.word = word;
        this.pos = pos;
        this.meaning = meaning;
        this.example = example;
        this.es_word = es_word;
        this.date = date;
        this.title = title;
        this.subtitle = subtitle;
    }

    public GlossaryDTO() {
    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getEs_word() {
        return es_word;
    }

    public void setEs_word(String es_word) {
        this.es_word = es_word;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Override
    public String toString() {
        return "GlossaryDTO{" +
                "_ID=" + _ID +
                ", id=" + id +
                ", word='" + word + '\'' +
                ", pos='" + pos + '\'' +
                ", meaning='" + meaning + '\'' +
                ", example='" + example + '\'' +
                ", es_word='" + es_word + '\'' +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                '}';
    }

}