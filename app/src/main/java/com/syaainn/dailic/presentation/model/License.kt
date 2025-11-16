package com.syaainn.dailic.presentation.model

import kotlinx.serialization.Serializable

@Serializable
enum class License(
    val title: String,
    val description: String
) {
    // 공통 직군
    DRIVING(
        title = "운전면허증",
        description = "현장직, 출장 및 납품 업무에 필수"
    ),
    KOREAN_HISTORY(
        title = "한국사능력검정시험",
        description = "공기업 가산점, 교양 수준 지식"
    ),
    COMPUTER_LEVEL1(
        title = "컴퓨터활용능력 1급",
        description = "엑셀 중심의 사무 실무 능력 평가"
    ),
    MOS(
        title = "MOS",
        description = "Microsoft Office 국제 공인 자격"
    ),
    TOEIC(
        title = "TOEIC",
        description = "전 직군 공통 어학능력 인증"
    ),

    // 공기업/공무원 직군
    SOCIAL_ANALYSIS(
        title = "사회조사분석사",
        description = "공공조사·통계분석 담당"
    ),
    LABOR_ATTORNEY(
        title = "공인노무사",
        description = "노동법·인사 관련 공공기관 진출"
    ),
    ADMINISTRATOR(
        title = "행정사",
        description = "민원·행정 대행업무 자격"
    ),
    APPRAISER(
        title = "감정평가사",
        description = "부동산 관련 공공기관 및 민간 진출"
    ),
    KBS_KOREAN(
        title = "KBS한국어능력시험",
        description = "공기업 공식 언어 능력 시험"
    ),

    // 회계/재무 직군
    ACCOUNTING_LEVEL1(
        title = "전산회계 1급",
        description = "재무제표 및 분개 업무 실무형 자격"
    ),
    TAX_LEVEL2(
        title = "전산세무 2급",
        description = "기초 세무회계 실무 적용"
    ),
    ERP(
        title = "ERP 정보관리사",
        description = "회계 시스템 기반 실무 자격"
    ),
    FAT(
        title = "FAT",
        description = "회계정보 처리 실무 중심 자격"
    ),
    AFPK(
        title = "AFPK",
        description = "재무설계 및 자산관리 전문가"
    ),

    // 보건/의료 직군
    NURSE(
        title = "간호사 면허",
        description = "병원 및 산업체 필수 전문면허"
    ),
    CLINICAL(
        title = "임상병리사",
        description = "병원 실험실 및 검진센터 필수"
    ),
    RADIOLOGIST(
        title = "방사선사",
        description = "영상의학 장비 운용"
    ),
    PHYSICAL_THERAPIST(
        title = "물리치료사",
        description = "재활 분야 필수 자격"
    ),
    COORDINATOR(
        title = "병원코디네이터",
        description = "의료 비서·서비스 중심 자격"
    ),

    // 정보기술 직군
    DEVELOPER(
        title = "정보처리기사",
        description = "개발자 필수 기술자격"
    ),
    SQLD(
        title = "SQLD",
        description = "데이터베이스 기본 설계 및 질의"
    ),
    ADSP(
        title = "ADSP",
        description = "데이터 분석 기초 자격"
    ),
    SECURITY(
        title = "정보보안기사",
        description = "보안 전문가용 기술자격"
    ),
    LINUX(
        title = "리눅스마스터 1급",
        description = "시스템 및 서버 관리 필수"
    )
}
