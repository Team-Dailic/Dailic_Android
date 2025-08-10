package com.syaainn.dailic.data.dummy

import com.syaainn.dailic.presentation.model.License
import com.syaainn.dailic.presentation.model.Occupation

enum class HomeDummy(
    val occupation: String,
    val license: String,
    val total: Int,
    val solved: Int
) {
    // 공통 직군
    DRIVING(
        occupation = Occupation.COMMON.name,
        license = License.DRIVING.name,
        total = 1000,
        solved = 247
    ),
    KOREAN_HISTORY(
        occupation = Occupation.COMMON.name,
        license = License.KOREAN_HISTORY.name,
        total = 800,
        solved = 560
    ),
    COMPUTER_LEVEL1(
        occupation = Occupation.COMMON.name,
        license = License.COMPUTER_LEVEL1.name,
        total = 1200,
        solved = 450
    ),
    MOS(
        occupation = Occupation.COMMON.name,
        license = License.MOS.name,
        total = 900,
        solved = 320
    ),
    TOEIC(
        occupation = Occupation.COMMON.name,
        license = License.TOEIC.name,
        total = 1500,
        solved = 780
    ),

    // 공기업/공무원 직군
    SOCIAL_ANALYSIS(
        occupation = Occupation.PUBLIC.name,
        license = License.SOCIAL_ANALYSIS.name,
        total = 600,
        solved = 120
    ),
    LABOR_ATTORNEY(
        occupation = Occupation.PUBLIC.name,
        license = License.LABOR_ATTORNEY.name,
        total = 800,
        solved = 180
    ),
    ADMINISTRATOR(
        occupation = Occupation.PUBLIC.name,
        license = License.ADMINISTRATOR.name,
        total = 900,
        solved = 300
    ),
    APPRAISER(
        occupation = Occupation.PUBLIC.name,
        license = License.APPRAISER.name,
        total = 700,
        solved = 150
    ),
    KBS_KOREAN(
        occupation = Occupation.PUBLIC.name,
        license = License.KBS_KOREAN.name,
        total = 1000,
        solved = 420
    ),

    // 회계/재무 직군
    ACCOUNTING_LEVEL1(
        occupation = Occupation.FINANCE.name,
        license = License.ACCOUNTING_LEVEL1.name,
        total = 1100,
        solved = 380
    ),
    TAX_LEVEL2(
        occupation = Occupation.FINANCE.name,
        license = License.TAX_LEVEL2.name,
        total = 900,
        solved = 290
    ),
    ERP(
        occupation = Occupation.FINANCE.name,
        license = License.ERP.name,
        total = 800,
        solved = 250
    ),
    FAT(
        occupation = Occupation.FINANCE.name,
        license = License.FAT.name,
        total = 700,
        solved = 180
    ),
    AFPK(
        occupation = Occupation.FINANCE.name,
        license = License.AFPK.name,
        total = 1000,
        solved = 320
    ),

    // 보건/의료 직군
    NURSE(
        occupation = Occupation.HEALTH.name,
        license = License.NURSE.name,
        total = 1500,
        solved = 600
    ),
    CLINICAL(
        occupation = Occupation.HEALTH.name,
        license = License.CLINICAL.name,
        total = 1200,
        solved = 450
    ),
    RADIOLOGIST(
        occupation = Occupation.HEALTH.name,
        license = License.RADIOLOGIST.name,
        total = 1000,
        solved = 380
    ),
    PHYSICAL_THERAPIST(
        occupation = Occupation.HEALTH.name,
        license = License.PHYSICAL_THERAPIST.name,
        total = 900,
        solved = 320
    ),
    COORDINATOR(
        occupation = Occupation.HEALTH.name,
        license = License.COORDINATOR.name,
        total = 800,
        solved = 280
    ),

    // 정보기술 직군
    DEVELOPER(
        occupation = Occupation.INTELLIGENCE.name,
        license = License.DEVELOPER.name,
        total = 1200,
        solved = 480
    ),
    SQLD(
        occupation = Occupation.INTELLIGENCE.name,
        license = License.SQLD.name,
        total = 700,
        solved = 150
    ),
    ADSP(
        occupation = Occupation.INTELLIGENCE.name,
        license = License.ADSP.name,
        total = 900,
        solved = 320
    ),
    SECURITY(
        occupation = Occupation.INTELLIGENCE.name,
        license = License.SECURITY.name,
        total = 1100,
        solved = 380
    ),
    LINUX(
        occupation = Occupation.INTELLIGENCE.name,
        license = License.LINUX.name,
        total = 800,
        solved = 250
    )
}