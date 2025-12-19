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
  schoolAtribute: string;
  email: string;
  phoneNumber: string;
  hasLoan: boolean;
}

export type MinUserResponse = {
  id: string;
  name: string;
  schoolAtribute: string;
  hasLoan: boolean;
}

export type MinUserPage = {
  content: MinUserResponse[];
  page: Page;
}
