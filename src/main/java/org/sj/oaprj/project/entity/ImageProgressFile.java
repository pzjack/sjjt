/**
 * 
 */
package org.sj.oaprj.project.entity;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

import org.sj.oaprj.entity.ID;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 形象进度附件
 * @author zhen.pan
 *
 */
@Entity(name="T_IMAGE_PROGRESS_FILE")
@Cacheable
public class ImageProgressFile extends ID {
	private static final long serialVersionUID = -4486142268178831738L;
	@Column(name = "FILE_TYPE")
	private Integer fileType;//资源类型,image 10,TV 20,other 30;
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPERVISIONDAILY_ID")
	private SupervisionDaily supervisionDaily;//监理日报对象
	@Column(name = "PATH")
	private String path;//本地文件路径
	@Column(name = "LOCAL_NAME")
	private String localname;//文件本地存储的名称
	@Column(name = "FILE_NAME")
	private String filename;//文件名称
	@Column(name = "THUMBNAIL")
	private Boolean thumbnail;//是否存在缩略图
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Temporal(javax.persistence.TemporalType.DATE)
	@Column(name = "REPORT_DATE")
	private Date reportDate;//上报日期
	public Integer getFileType() {
		return fileType;
	}
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	public SupervisionDaily getSupervisionDaily() {
		return supervisionDaily;
	}
	public void setSupervisionDaily(SupervisionDaily supervisionDaily) {
		this.supervisionDaily = supervisionDaily;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getLocalname() {
		return localname;
	}
	public void setLocalname(String localname) {
		this.localname = localname;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Boolean getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(Boolean thumbnail) {
		this.thumbnail = thumbnail;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
}