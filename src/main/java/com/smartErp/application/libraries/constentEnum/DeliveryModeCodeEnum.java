package com.smartErp.application.libraries.constentEnum;

public enum DeliveryModeCodeEnum {
	STD("Standard"),		//Standard Livraison à domicile normale (<30kg)
	TRK("Tracked"),		//Tracked 	Livraison à domicile en suivi (<30kg)
	REG("Registered"),		//Registered 	Livraison à domicile en recommandé (<30kg)
	MAG("Relay"),		//Relay		Livraison en magasin
	RCO("RelaisColis"),		//RelaisColis		Livraison en relais colis (Pick-up Points)
	SO1("SoColissimo"),		//SoColissimo		Livraison So Colissimo (Pick-up Points)
	REL("MondialRelay"),		//MondialRelay		Livraison Mondial Relais (Pick-up Points)
	LV1("BigParcelEco"),		//BigParcelEco		Livraison Gros colis Eco (>30kg)
	LV2("BigParcelStandard"),		//BigParcelStandard		Livraison Gros colis Standard (>30kg)
	LV3("BigParcelComfort"),		//BigParcelComfort		Livraison Gros colis Confort (>30kg)
	EXP("Express"),		//Express		Livraison Express (Express)
	FST("Fast");		//Fast		Livraison Rapide (Express)
 	
    String value;
    private DeliveryModeCodeEnum(String value) {
	    this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
