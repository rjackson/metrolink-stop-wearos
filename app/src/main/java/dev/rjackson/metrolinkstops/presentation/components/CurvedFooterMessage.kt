package dev.rjackson.metrolinkstops.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.wear.compose.foundation.*
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeTextDefaults
import androidx.wear.compose.material.curvedText

@Composable
fun CurvedFooterMessage(
    text: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = TimeTextDefaults.ContentPadding,
    timeTextStyle: TextStyle = TimeTextDefaults.timeTextStyle(),
    anchor: Float = 270f,
    anchorType: AnchorType = AnchorType.Center,
    radialAlignment: CurvedAlignment.Radial? = null,
    angularDirection: CurvedDirection.Angular = CurvedDirection.Angular.Normal,
) {
    if (LocalConfiguration.current.isScreenRound) {
        CurvedLayout(
            modifier = modifier,
            anchor = anchor,
            anchorType = anchorType,
            radialAlignment = radialAlignment,
            angularDirection = angularDirection,
        ) {
            curvedRow(
                modifier = CurvedModifier.padding(
                    ArcPaddingValues(
                        outer = contentPadding.calculateTopPadding(),
                        inner = contentPadding.calculateBottomPadding(),
                        before = contentPadding.calculateStartPadding(LayoutDirection.Ltr),
                        after = contentPadding.calculateEndPadding(LayoutDirection.Ltr),
                    )
                )
            ) {
                curvedText(
                    text = text,
                    style = CurvedTextStyle(timeTextStyle)
                )
            }
        }
    } else {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                maxLines = 1,
                style = timeTextStyle,
            )
        }
    }
}