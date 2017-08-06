#include <stdio.h>
#include <stdlib.h>

struct node {
    int data;
    struct node* next;
} ;

struct node* mergeList(struct node* a, struct node*  b)
{
    struct node* head = NULL;
    struct node* tail = NULL;
    while(a!=NULL&&b!=NULL){
        if(a->data<b->data){
            if(head==NULL){
                head = a;
                a = a->next;
                head ->next = NULL;
                tail = head;
            }else{
                tail ->next = a;
                a =  a->next;
                tail = tail->next;
                tail->next=NULL;
            }
        }else{
            if(head==NULL){
                head = b;
                b = b->next;
                head ->next = NULL;
                tail = head;
            }else{
                tail ->next = b;
                b = b->next;
                tail = tail->next;
                tail->next = NULL;
            }
        }
    }
    while(a!=NULL){
        if(head==NULL){
            head = a;
            a = a->next;
            head ->next = NULL;
            tail = head;
        }else{
            tail ->next = a;
            a = a->next;
            tail = tail->next;
            tail->next = NULL;
        }
    }
    while(b!=NULL){
        if(head==NULL){
            head = b;
            b = b->next;
            head ->next = NULL;
            tail = head;
        }else{
            tail ->next = b;
            b = b->next;
            tail = tail->next;
            tail->next = NULL;
        }
    }
    return head;
}

void splitList(struct node* source, struct node** a, struct node** b)
{
    int ctr = 0;
    struct node* temp = source;
    while(temp != NULL){
        temp = temp->next;
        ctr++;
    }
    int mid = ctr/2;
    (*a) = source;
    int t = 1;
    while(t<mid){
        source = source->next;
        t++;
    }
    (*b) = source ->next;
    source->next = NULL;
}

void mergeSort(struct node** headRef)
{
    struct node* head = *headRef;
    struct node* a;
    struct node* b;
    if ((head == NULL) || (head->next == NULL))
       return ;
    splitList(head, &a, &b);
    mergeSort(&a);
    mergeSort(&b);
    *headRef = mergeList(a, b);
}


void add_node(int data, struct node** head, struct node** tail){
    if(*head == NULL){
        *head = (struct node*)malloc(sizeof(struct node));
        (*head)->data = data;
        (*head)->next = NULL;
        *tail = *head;
    }else{
        struct node* temp = (struct node*)malloc(sizeof(struct node));
        temp->data = data;
        temp->next = NULL;

        (*tail)->next = temp;
        *tail = temp;
    }
}


int main()
{
    struct node* head = NULL;
    struct node* tail = NULL;
    int n;
    scanf("%d", &n);
    int i;
    for(i =0; i < n; i++){
        int data;
        scanf("%d",&data);
        add_node(data, &head, &tail);
    }
    mergeSort(&head);
    struct node* temp = head;
    while(temp!=NULL){
    	printf("%d ",temp->data);
    	temp = temp->next;
    }
}

