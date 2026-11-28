import { Attribute } from './custom.types';

export const BASE_URL: string = 'http://localhost:8080/api/v1';

export const SCHOOL_ATTRIBUTES: Attribute[] = [
  { label: 'Sexto', value: 'SEXTO' },
  { label: 'Sétimo', value: 'SETIMO' },
  { label: 'Oitavo', value: 'OITAVO' },
  { label: 'Nono', value: 'NONO' },
  { label: 'Primeira', value: 'PRIMEIRA' },
  { label: 'Segunda', value: 'SEGUNDA' },
  { label: 'Terceira', value: 'TERCEIRA' },
  { label: 'Funcionário', value: 'FUNCIONARIO' },
  { label: 'Outro', value: 'OUTRO' }
];

export function formatDate(date: string): string {
  return new Date(date).toLocaleString('pt-BR', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
    hour12: false
  });
}
