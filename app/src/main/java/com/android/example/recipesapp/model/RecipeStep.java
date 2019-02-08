package com.android.example.recipesapp.model;

/* RecipeStep holds json array data matching key: steps
steps               list / jason array
    id                  integer starting at 0
    shortDescription    string
    description         string
    videoURL            string / url
    thumbnailURL        string / url
 */

import android.os.Parcel;
import android.os.Parcelable;

public class RecipeStep implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public RecipeStep createFromParcel(Parcel in) {
            return new RecipeStep(in);
        }

        public RecipeStep[] newArray(int size) {
            return new RecipeStep[size];
        }
    };

    private Integer id;                  // integer starting at 0
    private String shortDescription;    // string
    private String description;         // string
    private String videoURL;            // string / url
    private String thumbnailURL;        // string / url

    public RecipeStep(Integer id, String shortDescription, String description, String videoURL,
                      String thumbnailURL) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    // Getter and Setter methods

    public Integer getId(){return id;}
    public String getShortDescription(){return shortDescription;}
    public String getDescription(){return description;}
    public String getVideoURL(){return videoURL;}
    public String getThumbnailURL(){return thumbnailURL;}

    public void setId(Integer id){this.id=id;}
    public void setShortDescription(String shortDescription){this.shortDescription=shortDescription;}
    public void setDescription(String description){this.description=description;}
    public void setVideoURL(String videoURL){this.videoURL=videoURL;}
    public void setThumbnailURL(String thumbnailURL){this.thumbnailURL=thumbnailURL;}

    public RecipeStep(Parcel in) {
        this.id = in.readInt();
        this.shortDescription=in.readString();
        this.description=in.readString();
        this.videoURL=in.readString();
        this.thumbnailURL=in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.shortDescription);
        dest.writeString(this.description);
        dest.writeString(this.videoURL);
        dest.writeString(this.thumbnailURL);
    }
}
