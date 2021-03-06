package greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table verses.
 */
public class Verse {

    private Long id;
    private Integer chapter;
    private Integer verse;
    private String book;
    private String header;
    private String subheader;
    private String text;

    public Verse() {
    }

    public Verse(Long id) {
        this.id = id;
    }

    public Verse(Long id, Integer chapter, Integer verse, String book, String header, String subheader, String text) {
        this.id = id;
        this.chapter = chapter;
        this.verse = verse;
        this.book = book;
        this.header = header;
        this.subheader = subheader;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getChapter() {
        return chapter;
    }

    public void setChapter(Integer chapter) {
        this.chapter = chapter;
    }

    public Integer getVerse() {
        return verse;
    }

    public void setVerse(Integer verse) {
        this.verse = verse;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSubheader() {
        return subheader;
    }

    public void setSubheader(String subheader) {
        this.subheader = subheader;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
