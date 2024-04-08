package com.hexagon.challenge.utils

class FormatData {
    companion object {
        fun formatCPF(cpf: String): String {
            return "${cpf.substring(0, 3)}.${cpf.substring(3, 6)}.${cpf.substring(6, 9)}-" +
                    cpf.substring(9, 11)
        }

        fun formatBirthDate(birthDate: String): String {
            return "${birthDate.substring(0, 2)}/${birthDate.substring(2, 4)}/" +
                    birthDate.substring(4, 8)
        }
    }
}