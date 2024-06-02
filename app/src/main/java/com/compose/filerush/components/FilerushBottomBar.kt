package com.compose.filerush.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AreaChart
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

import com.compose.filerush.R
import com.compose.filerush.ui.theme.FilerushTheme

enum class FilerushTabs(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    HOME(R.string.tab_home, Icons.Outlined.Home, "home"),
    HISTORY(R.string.tab_history, Icons.Outlined.Folder, "history"),
    NETWORK(R.string.tab_network, Icons.Outlined.AreaChart, "network"),
    SETTINGS(R.string.tab_settings, Icons.Outlined.Settings, "settings")
}

@Composable
fun FilerushBottomBar(
    tabs: Array<FilerushTabs>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(8.dp)
        ) {
            val tabRefs = (1..5).map { createRef() }

            createHorizontalChain(*tabRefs.toTypedArray(), chainStyle = ChainStyle.SpreadInside)

            tabs.slice(0..1).forEachIndexed { index, tab ->
                NavItem(
                    active = currentRoute == tab.route,
                    icon = tab.icon,
                    contentDescription = stringResource(id = tab.title),
                    modifier = Modifier
                        .constrainAs(tabRefs[index]) {
                            centerVerticallyTo(parent)
                        }
                )
            }

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .constrainAs(tabRefs[2]) {
                        centerVerticallyTo(parent)
                    },
                colors = IconButtonDefaults.outlinedIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp),
                    imageVector = Icons.Outlined.Add,
                    contentDescription = stringResource(id = R.string.add_download)
                )
            }

            tabs.slice(2..3).forEachIndexed { index, tab ->
                NavItem(
                    active = currentRoute == tab.route,
                    icon = tab.icon,
                    contentDescription = stringResource(id = tab.title),
                    modifier = Modifier
                        .constrainAs(tabRefs[index + 3]) {
                            centerVerticallyTo(parent)
                        }
                )
            }
        }
    }
}

@Composable
fun NavItem(
    active: Boolean,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    ConstraintLayout(
        modifier = modifier
            .padding(5.dp)
    ) {

        val (button, indicator) = createRefs()

        createVerticalChain(button, indicator)

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(button) {
                    centerHorizontallyTo(parent)
                }
        ) {
            Icon(
                modifier = Modifier,
                imageVector = icon,
                contentDescription = contentDescription,
                tint = if(active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            )
        }

        Box(
            modifier = Modifier
                .constrainAs(indicator) {
                    centerHorizontallyTo(parent)
                }
                .size(5.dp)
                .clip(CircleShape)
                .background(if (active) MaterialTheme.colorScheme.primary else Color.Transparent)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun FilerushBottomBarPreview() {
    FilerushTheme {
        Surface {
            FilerushBottomBar(
                tabs = FilerushTabs.entries.toTypedArray(),
                currentRoute = "home",
                navigateToRoute = {  }
            )
        }
    }
}