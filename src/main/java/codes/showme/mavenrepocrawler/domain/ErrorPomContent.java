package codes.showme.mavenrepocrawler.domain;

import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by jeremie on 2017/1/14.
 */
@Entity
@Table(name = "error_pom_content")
public class ErrorPomContent extends Model implements Serializable {
    @Column(name = "id")
    private long id;

    @Column(name = "link")
    private String link;

    @Column(name = "content")
    private String content;

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static ErrorPomContent get(long id) {
        return db().find(ErrorPomContent.class).where().eq("id", id).findUnique();
    }
}
