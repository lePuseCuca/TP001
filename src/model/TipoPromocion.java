package model;

public enum TipoPromocion {
	
	PORCENTUAL("Promo Porcentual"), 
	ABSOLUTA("Promo Absoluta"), 
	AxB("Promo AxB");
	
	private String descripcion;
	
	private TipoPromocion(String desc) {
		this.descripcion = desc;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	

}
