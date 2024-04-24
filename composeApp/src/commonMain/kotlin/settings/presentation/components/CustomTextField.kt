package settings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    placeHolder: String,
    text: String = "",
    onValueChange: (String) -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    errorMessage: String? = null,
    isError: Boolean = false,
    isVisible: Boolean = false,
    requiresDataValidation: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = false,
    maxLine: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val isKeyboardTypeNumber =
        keyboardType == KeyboardType.Phone || keyboardType == KeyboardType.Number
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusRequester = remember {
        FocusRequester()
    }
    val colorBorder = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSecondary

    val localStyle = LocalTextStyle.current
    val mergedStyle = localStyle.merge(TextStyle(color = LocalContentColor.current))
    Column {
        BasicTextField(
            value = text,
            onValueChange = {
                onValueChange(it)
            },
            readOnly = readOnly,
            maxLines = maxLine,
            singleLine = singleLine,
            interactionSource = interactionSource,
            textStyle = mergedStyle,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            visualTransformation =
            if (keyboardType == KeyboardType.Password) {
                if (isVisible) VisualTransformation.None else PasswordVisualTransformation()
            } else {
                if (visualTransformation != VisualTransformation.None && keyboardType == KeyboardType.Number) {
                    visualTransformation
                } else {
                    VisualTransformation.None
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(4.dp),
                            color = colorBorder
                        )
                        .background(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .focusRequester(focusRequester)
                ) {
                    if (leadingIcon != null) {
                        leadingIcon()
                    } else {
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                    Box(
                        modifier = Modifier
                            .weight(1.0f)
                            .padding(vertical = 16.dp)
                    ) {
                        if (text.isEmpty()) {
                            Text(
                                text = placeHolder,
                            )
                        }
                        Box(modifier = Modifier.fillMaxWidth()) {
                            innerTextField()
                        }
                    }
                    if (trailingIcon != null) {
                        trailingIcon()
                    } else {
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                }
            },
        )
        if (requiresDataValidation) {
            Text(
                text = if (isError) errorMessage!! else "",
                color = MaterialTheme.colorScheme.error,
                modifier = modifier
            )
        }
    }

}