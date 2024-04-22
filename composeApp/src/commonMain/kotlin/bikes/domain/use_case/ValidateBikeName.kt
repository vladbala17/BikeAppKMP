package bikes.domain.use_case


class ValidateBikeName {

    operator fun invoke(bikeName: String): ValidationResult {
        if (bikeName.isEmpty()) return ValidationResult(
            successful = false, errorMessage = "validation error"
        )
        return ValidationResult(successful = true, null)
    }
}