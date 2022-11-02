package support;

public class CategoryRecord {
	
	String categoryName;
	String categoryType;
	String subCategoryOf;
	Boolean updateCategoryName;
	Boolean updateCategoryType;
	Boolean updateSubcategoryOf;
	Boolean reassignTo;
	//Boolean update;
	
	
	
	
	
//	public void setUpdate(Boolean update) {
//		this.update = update;
//	}
	
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	
	public void setSubCategoryOf(String subCategoryOf) {
		this.subCategoryOf = subCategoryOf;
	}
	
	public void setUpdateCategoryName(Boolean update) {
		this.updateCategoryName = update;
	}
	
	public void setUpdateCategoryType(Boolean update) {
		this.updateCategoryType = update;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public void setUpdateSubcategoryOf(Boolean update) {
		this.updateSubcategoryOf = update;
	}
	
	public void setreassignTo(Boolean reassignTo) {
		this.reassignTo = reassignTo;
	}
	
	public String getCategoryName() {
		return this.categoryName;
	}
	
	public Boolean getreassignTo() {
		return this.reassignTo;
	}
	
	public String getCategoryType() {
		return this.categoryType;
	}
	public String getSubCategoryOf() {
		return this.subCategoryOf;
	}
	
	public Boolean getUpdate() {
		return this.updateCategoryName || this.updateCategoryType || this.updateSubcategoryOf;
	}
	
	
	public Boolean getUpdateCategoryName() {
		return this.updateCategoryName;
	}
	
	public Boolean getUpdateCategoryType() {
		return this.updateCategoryType;
	}
	
	public Boolean getUpdateSubcategoryOf() {
		return this.updateSubcategoryOf;
	}

}
