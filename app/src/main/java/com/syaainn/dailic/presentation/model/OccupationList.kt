package com.syaainn.dailic.presentation.model

enum class Occupation(
    val title: String,
    val licenseList: List<License>
) {
    COMMON(
        title = "공통 직군",
        licenseList = listOf(
            License("운전면허증", "현장직, 출장 및 납품 업무에 필수"),
            License("한국사능력검정시험", "공기업 가산점, 교양 수준 지식"),
            License("컴퓨터활용능력 1급", "엑셀 중심의 사무 실무 능력 평가"),
            License("MOS", "Microsoft Office 국제 공인 자격"),
            License("토익", "전 직군 공통 어학능력 인증")
        )
    ),
    PUBLIC(
        title = "공기업/공무원 직군",
        licenseList = listOf(
            License("사회조사분석사", "공공조사·통계분석 담당"),
            License("공인노무사", "노동법·인사 관련 공공기관 진출"),
            License("행정사", "민원·행정 대행업무 자격"),
            License("감정평가사", "부동산 관련 공공기관 및 민간 진출"),
            License("KBS한국어능력시험", "공기업 공식 언어 능력 시험")
        )
    ),
    FINANCE(
        title = "회계/재무 직군",
        licenseList = listOf(
            License("전산회계 1급", "재무제표 및 분개 업무 실무형 자격"),
            License("전산세무 2급", "기초 세무회계 실무 적용"),
            License("ERP 정보관리사", "회계 시스템 기반 실무 자격"),
            License("FAT", "회계정보 처리 실무 중심 자격"),
            License("AFPK", "재무설계 및 자산관리 전문가")
        )
    ),
    HEALTH(
        title = "보건/의료 직군",
        licenseList = listOf(
            License("간호사 면허", "병원 및 산업체 필수 전문면허"),
            License("임상병리사", "병원 실험실 및 검진센터 필수"),
            License("방사선사", "영상의학 장비 운용"),
            License("물리치료사", "재활 분야 필수 자격"),
            License("병원코디네이터", "의료 비서·서비스 중심 자격")
        )
    ),
    INTELLIGENCE(
        title = "정보기술(IT) 직군",
        licenseList = listOf(
            License("정보처리기사", "개발자 필수 기술자격"),
            License("SQLD", "데이터베이스 기본 설계 및 질의"),
            License("ADSP", "데이터 분석 기초 자격"),
            License("정보보안기사", "보안 전문가용 기술자격"),
            License("리눅스마스터 1급", "시스템 및 서버 관리 필수")
        )
    )
}

data class License(
    val name: String,
    val description: String
)