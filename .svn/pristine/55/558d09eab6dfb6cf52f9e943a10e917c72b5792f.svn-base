package com.migu.online.common;

/**
 * 证件类别
 * @author wangjin
 *
 */
public enum CertificateTypeEnum {

	residentIdentityard("A", "居民身份证"),
	organizationCodeCertificate("B", "组织机构代码证书"),
	certificateOfOfficers("C", "军官证"),
	soldierCard("D", "士兵证"),
	officerRetirementCertificate("E", "军官离退休证"),
	identificationOfOverseasPersonnel("F", "境外人员身份证明"),
	IdentificationOfDiplomaticPersonnel("G", "外交人员身份证明"),
	householdRegister("H", "居民户口簿"),
	permanentResidencePermitForForeigners("I", "外国人永久居留身份证"),
	certificateOfUnitCancellation("J", "单位注销证明"),
	residenceTemporaryResidenceCertificate("K", "居住暂住证明"),
	certificationInChina("L", "驻华机构证明"),
	temporaryResidentIdentityCard("M", "临时居民身份证"),
	unifiedSocialCreditCode("N", "统一社会信用代码"),
	businessCode("P", "个体工商户营业执照注册号");
    
    public final String code;
    public final String desc;

    CertificateTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public static CertificateTypeEnum getEnumByCode(String code) {
		for (CertificateTypeEnum sexEnum : CertificateTypeEnum.values()) {
			if (code.equals(sexEnum.getCode())) {
				return sexEnum;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
