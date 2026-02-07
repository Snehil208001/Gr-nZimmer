package com.grunzimmer.app.mainui.services.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grunzimmer.app.presentation.theme.LoginPrimary

// --- Data Classes ---
data class SimpleIncludedItem(val icon: ImageVector, val title: String)
data class DetailedIncludedItem(val icon: ImageVector, val title: String, val subtitle: String)

// --- Shared UI Components ---

@Composable
fun FormLabel(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF121714),
        modifier = Modifier.padding(bottom = 6.dp)
    )
}

@Composable
fun RowScope.SelectableChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) LoginPrimary.copy(alpha = 0.1f) else Color.White)
            .border(
                1.dp,
                if (isSelected) LoginPrimary else Color(0xFFDDE4E0),
                RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) LoginPrimary else Color(0xFF688274)
        )
    }
}

@Composable
fun SimpleIncludedItemCard(item: SimpleIncludedItem, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFDDE4E0), RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = null,
            tint = LoginPrimary,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = item.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF121714),
            lineHeight = 18.sp
        )
    }
}

@Composable
fun ChipButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    val bg = if (isSelected) Color(0xFF1F6F43) else Color.White
    val content = if (isSelected) Color.White else Color(0xFF4B5563)
    val border = if (isSelected) Color(0xFF1F6F43) else Color(0xFFE5E7EB)
    Box(Modifier.clip(RoundedCornerShape(50)).background(bg).border(1.dp, border, RoundedCornerShape(50)).clickable(onClick = onClick).padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(text, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = content)
    }
}

@Composable
fun SunlightChip(text: String, icon: ImageVector, isSelected: Boolean, onClick: () -> Unit) {
    val bg = if (isSelected) Color(0xFF1F6F43).copy(alpha = 0.1f) else Color.White
    val content = if (isSelected) Color(0xFF1F6F43) else Color(0xFF4B5563)
    val border = if (isSelected) Color(0xFF1F6F43) else Color(0xFFE5E7EB)
    Row(Modifier.clip(RoundedCornerShape(50)).background(bg).border(1.dp, border, RoundedCornerShape(50)).clickable(onClick = onClick).padding(horizontal = 16.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = if(isSelected) content else Color.LightGray, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(6.dp))
        Text(text, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = content)
    }
}

@Composable
fun DetailedIncludedItemRow(item: DetailedIncludedItem, primary: Color, text: Color, sub: Color) {
    Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.White).padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(40.dp).clip(CircleShape).background(primary.copy(alpha = 0.1f)), Alignment.Center) {
            Icon(item.icon, null, tint = primary, modifier = Modifier.size(24.dp))
        }
        Spacer(Modifier.width(16.dp))
        Column {
            Text(item.title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = text)
            Text(item.subtitle, fontSize = 12.sp, color = sub)
        }
    }
}

@Composable
fun DashedUploadBox(primary: Color) {
    val stroke = Stroke(width = 4f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f))
    Box(Modifier.fillMaxWidth().height(140.dp).clip(RoundedCornerShape(12.dp)).background(Color.White.copy(alpha = 0.5f)).drawBehind { drawRoundRect(color = Color.LightGray, style = stroke, cornerRadius = CornerRadius(12.dp.toPx())) }, Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(Modifier.size(48.dp).clip(CircleShape).background(Color(0xFFF3F4F6)), Alignment.Center) { Icon(Icons.Default.AddAPhoto, null, tint = Color.Gray) }
            Text("Tap to upload", fontSize = 14.sp, color = Color.Gray)
        }
    }
}