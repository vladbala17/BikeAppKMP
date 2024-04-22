package bikes.domain.use_case


class ValidateDistance {
    operator fun invoke(distance: String): ValidationResult {
        return if (distance.isEmpty()) {
            ValidationResult(false, "validation error")
        } else {
            ValidationResult(true, null)
        }
    }
}