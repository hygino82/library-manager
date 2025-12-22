export type Book = {
  id: string;
  title: string;
  author: string;
  personalCode: string;
  edition: number;
  publisher: string;
  totalPages: number;
  bookStatus: string;
  createdAt?: string;
  updatedAt?: string;
};

export type BookPage = {
  content: Book[];
  page: Page;
}

export type Page = {
  size: number;
  number: number;
  totalPages: number;
  totalElements: number;
}

export type BookRequest = {
  id?: string;
  title: string;
  author: string;
  personalCode: string;
  edition: number;
  publisher: string;
  totalPages: number;
};

export type User = {
  id?: string;
  name: string;
  schoolAttribute: string;
  email: string;
  phoneNumber: string;
  hasLoan: boolean;
  createdAt?: string;
  updatedAt?: string;
}

export type RequestUserDto = {
  name: string;
  schoolAttribute: string;
  email: string;
  phoneNumber: string;
}

export type MinUserResponse = {
  id: string;
  name: string;
  schoolAttribute: string;
  hasLoan: boolean;
}

export type MinUserPage = {
  content: MinUserResponse[];
  page: Page;
}

export type Attribute = {
  label: string;
  value: string;
}

export type RequestBookLoan = {
  bookId: string;
  userId: string;
}

export type BookLoan = {
  id: string;
  bookId: string;
  userId: string;
  bookTitle: string;
  userName: string;
  startDate: string;
  endDate: string;
  bookStatus: string;
  userHasLoan: boolean;
  hasActiveLoan: boolean;
}

export type ResponseBookLoan = {
  id: string;
  bookTitle: string;
  userName: string;
  startDate: string;
  endDate: string;
  hasActiveLoan: boolean;
}

export type ResponseLoanPage = {
  content: ResponseBookLoan[];
  page: Page;
}
