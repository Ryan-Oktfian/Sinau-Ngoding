package com.example.lazycolumnv2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lazycolumnv2.viewmodel.UserDetailViewModel
import com.example.lazycolumnv2.data.model.User

@Composable
fun UserDetailScreen(
    userId: Int,
    viewModel: UserDetailViewModel = viewModel()
) {
    val user = viewModel.user.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()

    LaunchedEffect(userId) {
        viewModel.fetchUserById(userId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Detail Pengguna",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        if (isLoading.value) {
            CircularProgressIndicator()
        } else if (errorMessage.value != null) {
            Text("Error: ${errorMessage.value}")
        } else {
            user.value?.let { u ->
                Text("Nama: ${u.name}", style = MaterialTheme.typography.titleMedium)
                Text("Username: ${u.username}")
                Text("Email: ${u.email}")
                Spacer(modifier = Modifier.height(8.dp))

                Text("Alamat:", style = MaterialTheme.typography.titleMedium)
                Text("${u.address.street}, ${u.address.suite}, ${u.address.city}")
                Spacer(modifier = Modifier.height(8.dp))

                Text("Telepon: ${u.phone}")
                Text("Website: ${u.website}")
                Spacer(modifier = Modifier.height(8.dp))

                Text("Perusahaan:", style = MaterialTheme.typography.titleMedium)
                Text("Nama: ${u.company.name}")
                Text("Moto: ${u.company.catchPhrase}")
                Text("Bidang: ${u.company.bs}")
            }
        }
    }
}