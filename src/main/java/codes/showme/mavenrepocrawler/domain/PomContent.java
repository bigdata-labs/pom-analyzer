package codes.showme.mavenrepocrawler.domain;

import io.ebean.Model;
import io.ebean.PagedList;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jack on 1/9/17.
 */
@Entity
@Table(name = "pom_content")
public class PomContent extends Model implements Serializable {

    private static final long serialVersionUID = 7787033845920836323L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    private long version;

    @Lob
    private String content;

    @Column(name = "link", length = 2048)
    private String link;

    public PomContent(String link, String pomContent) {
        this.link = link;
        this.content = pomContent;
    }

    public PomContent(long id, long version, String content, String link) {
        this.id = id;
        this.version = version;
        this.content = content;
        this.link = link;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static int countPomContent() {
        return db().find(PomContent.class).findCount();
    }

    public static PagedList<PomContent> listPomContentOrderById(int pageIndex, int pageSize) {
        return db().find(PomContent.class)
                .where()
                .order().asc("id")
                .setFirstRow(pageIndex * pageSize)
                .setMaxRows(pageSize)
                .findPagedList();
    }

    public static PomContent get(long id) {
        return db().find(PomContent.class).where().eq("id", id).findUnique();
    }
}
