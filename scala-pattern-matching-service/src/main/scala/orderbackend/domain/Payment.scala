package orderbackend.domain

enum Payment:
  case Pix
  case CreditCard
  case DebitCard
  case Cash