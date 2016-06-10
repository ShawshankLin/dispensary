package com.dispensary.project.utils;

public class TreeNode {  
    private long id;  
    private long pId;  
    private String name;  
    private String idt;
    private Boolean checked;  
    private Boolean open;  
    private String menuId;
    private String authorityId;
    

	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}
	public String getIdt() {
		return idt;
	}
	public void setIdt(String idt) {
		this.idt = idt;
	}
	/** 
     * @return the id 
     */  
    public Long getId() {  
        return id;  
    }  
    /** 
     * @param id the id to set 
     */  
    public void setId(Long id) {  
        this.id = id;  
    }  
    /** 
     * @return the pId 
     */  
    public Long getPId() {  
        return pId;  
    }  
    /** 
     * @param id the pId to set 
     */  
    public void setPId(Long id) {  
        pId = id;  
    }  
    /** 
     * @return the name 
     */  
    public String getName() {  
        return name;  
    }  
    /** 
     * @param name the name to set 
     */  
    public void setName(String name) {  
        this.name = name;  
    }  
    /** 
     * @return the checked 
     */  
    public Boolean isChecked() {  
        return checked;  
    }  
    /** 
     * @param checked the checked to set 
     */  
    public void setChecked(Boolean checked) {  
        this.checked = checked;  
    }  
    /** 
     * @return the open 
     */  
    public Boolean isOpen() {  
        return open;  
    }  
    /** 
     * @param open the open to set 
     */  
    public void setOpen(Boolean open) {  
        this.open = open;  
    }  

    
    public TreeNode(long id, long pId, String name, Boolean checked, Boolean open,String idt) {  
        super();  
        this.id = id;  
        this.pId = pId;  
        this.name = name;  
        this.checked = checked;  
        this.open = open;  
        this.idt=idt;
    }  
    
    public TreeNode(long id, long pId, String name, Boolean checked, Boolean open,String menuId,String authorityId) {  
        super();  
        this.id = id;  
        this.pId = pId;  
        this.name = name;  
        this.checked = checked;  
        this.open = open;  
        this.menuId=menuId;
        this.authorityId=authorityId;
    }  
    
    public TreeNode() {  
        super();  
    }  
      
  
}  