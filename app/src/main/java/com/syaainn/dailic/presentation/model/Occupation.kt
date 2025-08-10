package com.syaainn.dailic.presentation.model

import kotlinx.serialization.Serializable

@Serializable
enum class Occupation(
    val title: String,
    val licenseList: List<License>
) {
    COMMON(
        title = "공통 직군",
        licenseList = listOf(
            License.DRIVING,
            License.KOREAN_HISTORY,
            License.COMPUTER_LEVEL1,
            License.MOS,
            License.TOEIC
        )
    ),
    PUBLIC(
        title = "공기업/공무원 직군",
        licenseList = listOf(
            License.SOCIAL_ANALYSIS,
            License.LABOR_ATTORNEY,
            License.ADMINISTRATOR,
            License.APPRAISER,
            License.KBS_KOREAN
        )
    ),
    FINANCE(
        title = "회계/재무 직군",
        licenseList = listOf(
            License.ACCOUNTING_LEVEL1,
            License.TAX_LEVEL2,
            License.ERP,
            License.FAT,
            License.AFPK
        )
    ),
    HEALTH(
        title = "보건/의료 직군",
        licenseList = listOf(
            License.NURSE,
            License.CLINICAL,
            License.RADIOLOGIST,
            License.PHYSICAL_THERAPIST,
            License.COORDINATOR
        )
    ),
    INTELLIGENCE(
        title = "정보기술(IT) 직군",
        licenseList = listOf(
            License.DEVELOPER,
            License.SQLD,
            License.ADSP,
            License.SECURITY,
            License.LINUX
        )
    )
}
