package com.example.emptyviewsactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.emptyviewsactivity.ui.theme.EmptyViewsActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmptyViewsActivityTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PortfolioScreen()
                }
            }
        }
    }
}

@Composable
fun PortfolioScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            item { HeroSection() }
            item { ExperienceSection(experiences = sampleExperiences) }
            item { SkillsSection(skills = skillSet) }
            item { ProjectsSection(projects = featuredProjects) }
            item { ContactSection() }
        }
    }
}

@Composable
private fun HeroSection(modifier: Modifier = Modifier) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.primary.copy(alpha = 0.92f),
            MaterialTheme.colorScheme.secondary
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = gradient,
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(28.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AssistChip(
                onClick = {},
                label = { Text("モバイル × Web エンジニア") },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    labelColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Kenta Sato",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "Androidとフロントエンドを横断し、体験設計から実装・改善までをリードします。",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "モバイルアプリを中心に10年以上の開発経験。ビジネスゴールとユーザー体験を両立させる開発体制づくりが得意です。",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(
                    onClick = { /* TODO: open contact */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("最新の実績を見る")
                }
                OutlinedButton(
                    onClick = { /* TODO: download resume */ },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f))
                ) {
                    Text("職務経歴書を請求")
                }
            }
        }
    }
}

@Composable
private fun ExperienceSection(experiences: List<Experience>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SectionTitle(
            title = "経験",
            description = "ユーザー価値とスピードを両立させるための開発と仕組み化を担当しました"
        )
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            experiences.forEach { experience ->
                ExperienceCard(experience = experience)
            }
        }
    }
}

@Composable
private fun ExperienceCard(experience: Experience, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = experience.role,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "${experience.company} ・ ${experience.period}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = experience.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            HighlightRow(highlights = experience.highlights)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun HighlightRow(highlights: List<String>, modifier: Modifier = Modifier) {
    if (highlights.isEmpty()) return

    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        highlights.forEach { highlight ->
            AssistChip(
                onClick = {},
                label = { Text(highlight) },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    labelColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SkillsSection(skills: List<String>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SectionTitle(
            title = "スキル",
            description = "ビルドから運用までを支える技術群とプロダクト作りの知見"
        )
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
        ) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                skills.forEach { skill ->
                    AssistChip(
                        onClick = {},
                        label = { Text(skill) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ProjectsSection(projects: List<Project>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SectionTitle(
            title = "プロジェクト",
            description = "インハウス開発から共同開発まで、事業成長に直結する改善を行ってきました"
        )
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            projects.forEach { project ->
                ProjectCard(project = project)
            }
        }
    }
}

@Composable
private fun ProjectCard(project: Project, modifier: Modifier = Modifier) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = CardDefaults.outlinedCardBorder(),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = project.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = project.stack,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = project.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = project.impact,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ContactSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SectionTitle(
            title = "コンタクト",
            description = "開発リード、技術顧問、チーム改善のご相談を歓迎しています"
        )
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "hello@yourname.dev",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "GitHub: https://github.com/yourname",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "note: https://note.com/yourname",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String, description: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Immutable
data class Experience(
    val role: String,
    val company: String,
    val period: String,
    val description: String,
    val highlights: List<String>
)

@Immutable
data class Project(
    val name: String,
    val stack: String,
    val description: String,
    val impact: String
)

private val sampleExperiences = listOf(
    Experience(
        role = "リードAndroidエンジニア",
        company = "SmartRetail株式会社",
        period = "2021 - 現在",
        description = "Jetpack ComposeとKotlin Multiplatformでアプリ基盤を刷新し、リリースから半年で継続率を125%まで引き上げました。",
        highlights = listOf("Jetpack Compose", "Kotlin", "KMP", "Design System", "Firebase", "Play Console 4.8★")
    ),
    Experience(
        role = "フロントエンドエンジニア",
        company = "SaaSスタートアップB",
        period = "2018 - 2021",
        description = "TypeScript + Next.jsでプロダクトのコア体験を実装。UXリサーチとグロース施策を並走し、CVRを1.6倍改善。",
        highlights = listOf("TypeScript", "Next.js", "React", "Storybook", "A/Bテスト", "GraphQL")
    ),
    Experience(
        role = "ソフトウェアエンジニア",
        company = "エンタープライズ系企業C",
        period = "2015 - 2018",
        description = "Java/Kotlinとクラウドネイティブ基盤で業務システムを内製化。CI/CDパイプラインを構築し、デリバリーを週次に高速化。",
        highlights = listOf("Java", "Spring", "Kotlin", "Docker", "Kubernetes", "CI/CD")
    )
)

private val skillSet = listOf(
    "Kotlin",
    "Jetpack Compose",
    "Kotlin Multiplatform",
    "TypeScript",
    "Next.js",
    "React",
    "Python",
    "FastAPI",
    "GraphQL",
    "Design System",
    "Firebase",
    "AWS",
    "Kubernetes",
    "CI/CD",
    "チームビルディング"
)

private val featuredProjects = listOf(
    Project(
        name = "SmartRetail Companion App",
        stack = "Kotlin / Compose / Firebase / Cloud Run",
        description = "在庫管理と分析をワンストップで行うリテール向けモバイルアプリ。店舗スタッフが日常で使える体験設計を主導しました。",
        impact = "β版から本番までリードし、NPS +18pt・オペレーションコスト20%削減を達成。"
    ),
    Project(
        name = "Realtime Analytics Dashboard",
        stack = "TypeScript / Next.js / GraphQL / Hasura",
        description = "ビジネス指標をリアルタイムで可視化するダッシュボード。プロダクトオーナーと共創し、意思決定のリードタイムを短縮。",
        impact = "リリース後3ヶ月でMAUが2.2倍、KPIのモニタリング工数を60%削減。"
    ),
    Project(
        name = "DataOps Automation Suite",
        stack = "Python / Airflow / AWS",
        description = "データパイプラインの自動化と監視を統合したプラットフォーム。異常検知とセルフヒーリング機構を実装。",
        impact = "1年で障害件数を70%削減し、データ活用のサイクルを高速化。"
    )
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PortfolioPreview() {
    EmptyViewsActivityTheme {
        PortfolioScreen()
    }
}
