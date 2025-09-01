package ru.skypro.homework.model;

import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "avatar_ad")
@Entity
public class AvatarAdModel {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, name = "file_patch")
    private String filePath;
    @Column(nullable = false, name = "media_type")
    private String mediaType;
    @Column(nullable = false, name = "file_size")
    private long fileSize;
    @Lob
    @Column(name = "data")
    private byte[] data;
    @OneToOne
    @JoinColumn(name = "ad_pk")
    private AdModel adModel;

    public AvatarAdModel() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof AvatarAdModel)) return false;
        AvatarAdModel that = (AvatarAdModel) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AvatarAdModel{" +
                "adModel=" + adModel +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", filePath='" + filePath + '\'' +
                ", id=" + id +
                '}';
    }

    public Integer getId() {
        return this.id;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public byte[] getData() {
        return this.data;
    }

    public AdModel getAdModel() {
        return this.adModel;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setAdModel(AdModel adModel) {
        this.adModel = adModel;
    }
}
